// 12. Write a simple program for implementation of persistenCookieStore.

import java.io.*;
import java.net.*;
import java.util.*;
import java.net.CookieHandler;

public class PersistentCookieHandler {

    public static void main(String[] args) {
        try {
            // Set the default CookieManager with a custom PersistentCookieStore
            PersistentCookieStore cookieStore = new PersistentCookieStore();
            CookieManager cookieManager = new CookieManager(cookieStore, CookiePolicy.ACCEPT_ALL);
            CookieHandler.setDefault(cookieManager);

            // Create a URL object
            URI uri = new URI("http://www.google.com");
            URL url = uri.toURL();

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method to GET
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Print the response
            System.out.println("URL: www.google.com");

            // Print the cookies stored in the PersistentCookieStore
            System.out.println("Cookies:");
            cookieStore.getCookies().forEach(cookie -> {
                System.out.println(cookie);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class PersistentCookieStore implements CookieStore {

        private static final String COOKIE_FILE = "cookies.dat";
        private final Map<URI, List<SerializableHttpCookie>> cookieMap;

        public PersistentCookieStore() {
            cookieMap = new HashMap<>();
            loadCookies();
        }

        @Override
        public void add(URI uri, HttpCookie cookie) {
            List<SerializableHttpCookie> cookies = cookieMap.getOrDefault(uri, new ArrayList<>());
            cookies.add(new SerializableHttpCookie(cookie));
            cookieMap.put(uri, cookies);
            saveCookies();
        }

        @Override
        public List<HttpCookie> get(URI uri) {
            List<SerializableHttpCookie> serializableCookies = cookieMap.getOrDefault(uri, new ArrayList<>());
            List<HttpCookie> cookies = new ArrayList<>();
            for (SerializableHttpCookie serializableCookie : serializableCookies) {
                cookies.add(serializableCookie.getCookie());
            }
            return cookies;
        }

        @Override
        public List<HttpCookie> getCookies() {
            List<HttpCookie> allCookies = new ArrayList<>();
            for (List<SerializableHttpCookie> serializableCookies : cookieMap.values()) {
                for (SerializableHttpCookie serializableCookie : serializableCookies) {
                    allCookies.add(serializableCookie.getCookie());
                }
            }
            return allCookies;
        }

        @Override
        public List<URI> getURIs() {
            return new ArrayList<>(cookieMap.keySet());
        }

        @Override
        public boolean remove(URI uri, HttpCookie cookie) {
            List<SerializableHttpCookie> cookies = cookieMap.get(uri);
            if (cookies != null) {
                boolean removed = cookies.removeIf(serializableCookie -> serializableCookie.getCookie().equals(cookie));
                if (removed) {
                    saveCookies();
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean removeAll() {
            cookieMap.clear();
            saveCookies();
            return true;
        }

        private void saveCookies() {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(COOKIE_FILE))) {
                oos.writeObject(cookieMap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @SuppressWarnings("unchecked")
        private void loadCookies() {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(COOKIE_FILE))) {
                Object readObject = ois.readObject();
                if (readObject instanceof Map) {
                    cookieMap.putAll((Map<URI, List<SerializableHttpCookie>>) readObject);
                }
            } catch (IOException | ClassNotFoundException e) {
                // It's okay if the file doesn't exist or can't be read; we'll start with an empty store.
            }
        }
    }

    public static class SerializableHttpCookie implements Serializable {
        private static final long serialVersionUID = 1L;

        private transient HttpCookie cookie;

        public SerializableHttpCookie(HttpCookie cookie) {
            this.cookie = cookie;
        }

        public HttpCookie getCookie() {
            return cookie;
        }

        private void writeObject(ObjectOutputStream oos) throws IOException {
            oos.defaultWriteObject();
            oos.writeUTF(cookie.getName());
            oos.writeUTF(cookie.getValue());
            oos.writeObject(cookie.getComment());
            oos.writeObject(cookie.getCommentURL());
            oos.writeBoolean(cookie.getDiscard());
            oos.writeUTF(cookie.getDomain());
            oos.writeLong(cookie.getMaxAge());
            oos.writeUTF(cookie.getPath());
            oos.writeBoolean(cookie.getSecure());
            oos.writeInt(cookie.getVersion());
        }

        private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
            ois.defaultReadObject();
            String name = ois.readUTF();
            String value = ois.readUTF();
            cookie = new HttpCookie(name, value);
            cookie.setComment((String) ois.readObject());
            cookie.setCommentURL((String) ois.readObject());
            cookie.setDiscard(ois.readBoolean());
            cookie.setDomain(ois.readUTF());
            cookie.setMaxAge(ois.readLong());
            cookie.setPath(ois.readUTF());
            cookie.setSecure(ois.readBoolean());
            cookie.setVersion(ois.readInt());
        }
    }
}

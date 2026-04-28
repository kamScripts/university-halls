package uni.universityhalls;

import java.io.*;

/** Utility Class responsible for data persistence
 *
 */
public class StoreRepository {
    public static void save(Store store, String filepath) {
        File f = new File(filepath);
        System.out.println("Saving to: " + f.getAbsolutePath());

        try (
                FileOutputStream storeFile = new FileOutputStream(f);
                ObjectOutputStream storeStream = new ObjectOutputStream(storeFile)
        ) {
            storeStream.writeObject(store);
            System.out.println("Object saved successfully!");
        } catch (IOException e) {
            System.out.println("Problem occurred while writing operation");
            e.printStackTrace();
        }
    }

    public static Store load(String filepath) {
        File f = new File(filepath);
        System.out.println("Loading from: " + f.getAbsolutePath());

        try (
                FileInputStream storeFile = new FileInputStream(f);
                ObjectInputStream storeStream = new ObjectInputStream(storeFile)
        ) {
            return (Store) storeStream.readObject();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + f.getAbsolutePath());
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            System.out.println("Class mismatch while reading file");
            e.printStackTrace();

        } catch (StreamCorruptedException e) {
            System.out.println("Unreadable or corrupted file");
            e.printStackTrace();

        } catch (IOException e) {
            System.out.println("Problem occurred while reading operation");
            e.printStackTrace();
        }

        return null;
    }
}

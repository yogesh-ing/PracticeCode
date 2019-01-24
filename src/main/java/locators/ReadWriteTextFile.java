String directory = System.getProperty("user.home");  
String fileName = "sample.txt";

String content = "This is a sample text.";  
Path path = Paths.get(directory, fileName);

try {  
    Files.write(path, content.getBytes(), StandardOpenOption.CREATE);
} catch (IOException e) {
    // exception handling
}

try {  
    List<String> list = Files.readAllLines(path);
    list.forEach(line -> System.out.println(line));
} catch (IOException e) {
    // exception handling
}





Another way to retrieve the content via the Files class, which is more important if you're not reading text data, is to use the readAllBytes method to read the data in to a byte array:

try {  
    byte[] data = Files.readAllBytes(path);
    System.out.println(new String(data));
} catch (IOException e) {
    // exception handling
}




In case you are interested in using streams with java.nio, you can also use the below methods provided by the Files class, which work just like the streams we covered earlier in the article:

Files.newBufferedReader(path)  
Files.newBufferedWriter(path, options)  
Files.newInputStream(path, options)  
Files.newOutputStream(path, options)  
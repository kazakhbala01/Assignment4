// This is a class that represents a testing object with an ID and a name
import java.util.Random;
public class MyTestingClass {
    private int ID;
    private String Name;

    public MyTestingClass(int ID, String Name){
        this.ID=ID;
        this.Name=Name;
    }

    @Override
    public int hashCode() {
        int HashValue=37;
        // The hash value is computed using a formula that involves the ID and the name of the object
        HashValue=41*HashValue+ID;
        if (Name==null) {
            HashValue=41*HashValue;
        } else {
            HashValue=41*HashValue+Name.hashCode();
        }
        return HashValue;
    }
    // This is the main method that tests the hash table class
    public static void main(String[]args){
        MyHashTable<MyTestingClass, Student> table= new MyHashTable<>();
        MyHashTable<MyTestingClass, Student> table2= new MyHashTable<>();
        Random random=new Random();

        // This is a loop that inserts 10000 key-value pairs into the hash table
        for(int i=0;i<10000;i++){
            // The key is a testing object with a random ID and a name based on the loop index
            MyTestingClass key=new MyTestingClass(random.nextInt(1000),"Name"+i);
            // The value is a student object with a name based on the loop index and a gender based on the parity of the index
            Student value=new Student("Student"+i,i%2==0?"male":"female");
            // The key-value pair is inserted into the hash table using the put method
            table.put(key,value);
            table2.put(key,value);
        }

        // This is an array that stores the size of each chain in the hash table using the getBucketSize method
        int[]NumBucket=table.getBucketSize();
        System.out.println(NumBucket[1]);
        // This is a loop that prints out the size of each chain in the hash table
        for (int i = 0; i < NumBucket.length; i++) {
            System.out.println("Bucket "+i+" has: "+NumBucket[i]+" elements");
        }
    }
}
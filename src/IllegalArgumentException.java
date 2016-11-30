/**
 * Created by antoinewehenkel on 29/11/16.
 */
public class IllegalArgumentException extends Throwable {
    public IllegalArgumentException(int i, int j) {
        System.err.println("Bad argument exception. Error occured at element: " + i +", " + j + ".");
    }
}

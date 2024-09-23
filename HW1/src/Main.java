import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Integer[] array = {5,18,24,66,137,11,1,57,88,76,54,86,25,74,16,85,46,54};
        List<Integer> array1=Arrays.asList(array);
        System.out.println("Среднее значение всех четных чисел =" + getAverageEvenFromList(array1));

    }
    public static double getAverageEvenFromList(List<Integer> list){

        double average = list.stream()
                .filter(s->((s % 2)==0))
                .mapToDouble(s->s)
                .average()
                .orElse(Double.NaN);
        return average;
    }
}
import java.util.*;
import java.util.stream.*;

public class Main {
  public static void main(String[] args) throws Exception {
    for (int j = 0; j < 10000; j++) {
      try {
        List<String> fileNmList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
          fileNmList.add(i + "");
        }
        HashMap<String, List<String>> result = fileNmList.parallelStream().reduce(new HashMap<String, List<String>>(), (fileMap1, fileNm1) -> {
          fileMap1.put(fileNm1, Arrays.asList("v"));
          return fileMap1;
        }, (fileMap2, fileMap3) -> {
          fileMap2.putAll(fileMap3);
          return fileMap2;
        });
        Set<String> keys = result.keySet();
        if (keys.size() != 5) {
          System.out.println(j);

          System.out.println(keys.size());
          System.out.println(keys);
        }
      } catch (ConcurrentModificationException e) {
        System.out.println(e);
      }
    }
  }
}
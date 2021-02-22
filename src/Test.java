import java.util.HashSet;
import java.util.Set;

public class Test {

    public static int[] doDijkstra(int[][] d, int from) {
        Set<Integer> set = new HashSet<Integer>();
        set.add(from);

        boolean[] closed = new boolean[d.length];
        int[] distances = new int[d.length];
        for (int i = 0; i < d.length; i++) {
            if (i != from) {
                distances[i] = Integer.MAX_VALUE;
            } else {
                distances[i] = 0;
            }
        }

        int[] predecessors = new int[d.length];
        predecessors[from] = -1;

        while (!set.isEmpty()) {
            int minDistance = Integer.MAX_VALUE;
            int node = -1;
            for (Integer i : set) {
                if (distances[i] < minDistance) {
                    minDistance = distances[i];
                    node = i;
                }
            }

            set.remove(node);
            closed[node] = true;

            for (int i = 0; i < d.length; i++) {
                if (d[node][i] != Integer.MAX_VALUE) {
                    if (!closed[i]) {
                        if (distances[node] + d[node][i] < distances[i]) {
                            distances[i] = distances[node] + d[node][i];
                            predecessors[i] = node;
                            set.add(i);
                        }
                    }
                }
            }
        }
        return predecessors;
    }
}

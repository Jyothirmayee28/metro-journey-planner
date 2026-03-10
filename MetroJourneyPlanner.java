import java.util.*;

class MetroJourneyPlanner {

    static class Edge {
        String destination;
        int distance;

        Edge(String destination, int distance) {
            this.destination = destination;
            this.distance = distance;
        }
    }

    static Map<String, List<Edge>> metroMap = new HashMap<>();

    static void addConnection(String source, String destination, int distance) {
        metroMap.putIfAbsent(source, new ArrayList<>());
        metroMap.putIfAbsent(destination, new ArrayList<>());

        metroMap.get(source).add(new Edge(destination, distance));
        metroMap.get(destination).add(new Edge(source, distance));
    }

    static void shortestPath(String start) {

        Map<String, Integer> distance = new HashMap<>();
        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(distance::get));

        for (String station : metroMap.keySet()) {
            distance.put(station, Integer.MAX_VALUE);
        }

        distance.put(start, 0);
        pq.add(start);

        while (!pq.isEmpty()) {
            String current = pq.poll();

            for (Edge edge : metroMap.get(current)) {
                int newDist = distance.get(current) + edge.distance;

                if (newDist < distance.get(edge.destination)) {
                    distance.put(edge.destination, newDist);
                    pq.add(edge.destination);
                }
            }
        }

        for (String station : distance.keySet()) {
            System.out.println(start + " -> " + station + " : " + distance.get(station) + " km");
        }
    }

    public static void main(String[] args) {

        addConnection("Ameerpet", "SR Nagar", 2);
        addConnection("SR Nagar", "ESI", 2);
        addConnection("ESI", "Erragadda", 3);
        addConnection("Erragadda", "Miyapur", 4);

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter starting station:");
        String start = sc.nextLine();

        shortestPath(start);
    }
}

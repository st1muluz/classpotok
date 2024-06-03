public class УчебнаяГруппа {
    private String name;

    public УчебнаяГруппа(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

import java.util.Iterator;
import java.util.List;

public class Поток implements Iterable<УчебнаяГруппа> {
    private List<УчебнаяГруппа> группы;

    public Поток(List<УчебнаяГруппа> группы) {
        this.группы = группы;
    }

    public List<УчебнаяГруппа> getГруппы() {
        return группы;
    }

    @Override
    public Iterator<УчебнаяГруппа> iterator() {
        return группы.iterator();
    }
}

import java.util.Comparator;

public class StreamComparator implements Comparator<Поток> {
    @Override
    public int compare(Поток p1, Поток p2) {
        return Integer.compare(p1.getГруппы().size(), p2.getГруппы().size());
    }
}

import java.util.Collections;
import java.util.List;

public class ПотокСервис {
    public void sortStreams(List<Поток> потоки) {
        Collections.sort(потоки, new StreamComparator());
    }
}

import java.util.List;

public class Контроллер {
    private ПотокСервис потокСервис;

    public Контроллер() {
        this.потокСервис = new ПотокСервис();
    }

    public void sortStreams(List<Поток> потоки) {
        потокСервис.sortStreams(потоки);
    }
}

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        УчебнаяГруппа group1 = new УчебнаяГруппа("Группа 1");
        УчебнаяГруппа group2 = new УчебнаяГруппа("Группа 2");
        УчебнаяГруппа group3 = new УчебнаяГруппа("Группа 3");

        Поток поток1 = new Поток(Arrays.asList(group1));
        Поток поток2 = new Поток(Arrays.asList(group1, group2));
        Поток поток3 = new Поток(Arrays.asList(group1, group2, group3));

        List<Поток> потоки = Arrays.asList(поток1, поток2, поток3);

        Контроллер контроллер = new Контроллер();
        контроллер.sortStreams(потоки);

        for (Поток поток : потоки) {
            System.out.println("Поток с " + поток.getГруппы().size() + " группами.");
        }
    }
}
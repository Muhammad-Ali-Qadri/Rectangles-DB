package quadtree;

import processor.Rectangle;
import skiplist.KVPair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * This implementation of a tree node represents a leaf node in the
 * PRQuadTree implementation.
 *
 * @param <K> the type of key stored in node
 * @author Muhammad Ali Qadri
 * @version 1
 */
public class LeafTreeNode<K extends Comparable<? super K>>
        implements TreeNode<K> {

    //Represents the list of key-point pairs stored in this leaf node
    private final List<KVPair<K, Point>> pairs;

    public LeafTreeNode() {
        pairs = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TreeNode<K> insert(KVPair<K, Point> pair, Point start,
                              int width) {
        //TODO: add find all method to the skiplist class

        boolean foundPoint = false;

        for (KVPair<K, Point> listPoint : pairs) {
            if (pair.getValue().equals(listPoint.getValue())) {
                foundPoint = true;
                break;
            }
        }

        pairs.add(pair);

        if (foundPoint || pairs.size() <= 3) {
            return this;
        }
        else {
            TreeNode<K> internal = new IntTreeNode<>();

            for (KVPair<K, Point> listPoint : pairs) {
                internal = internal.insert(listPoint, start, width);
            }

            return internal;
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public TreeNode<K> removeByValue(Point point, Point start, int width,
                                     List<KVPair<K, Point>> pairs) {

        KVPair<K, Point> foundPair = null;
        for (KVPair<K, Point> listPoint : this.pairs) {
            if (point.equals(listPoint.getValue())) {
                foundPair = listPoint;
                break;
            }
        }

        if (foundPair == null) {
            return this;
        }

        pairs.add(foundPair);
        this.pairs.remove(foundPair);

        if (this.pairs.size() == 0) {
            return EmptyTreeNode.getInstance();
        }

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void duplicates(List<Point> duplicates) {
        duplicates.addAll(
                pairs.stream().filter(x ->
                                pairs.stream()
                                        .filter(y -> y.getValue()
                                                .equals(x.getValue()
                                                )).count() > 1)
                        .map(KVPair::getValue).distinct()
                        .collect(Collectors.toList())
        );
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int regionSearch(Rectangle searchRect,
                            Point CurrentRegionStart, int currentRegionWidth,
                            List<KVPair<K, Point>> searchPoints) {
        searchPoints.addAll(pairs.stream().filter(x ->
                searchRect.contains(x.getValue().getX(),
                        x.getValue().getY())
        ).collect(Collectors.toList()));
        return 1;
    }


    /**
     * {@inheritDoc}
     */
    public List<KVPair<K, Point>> getKeyValuePairs() {
        return pairs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int dump(int level, Point start, int width,
                    StringBuilder treeStringBuilder) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("  ");
        }

        treeStringBuilder.append(sb).append("Node at ")
                .append(start).append(", ").append(width).append(":\n");

        for (KVPair<K, Point> pair : pairs) {
            treeStringBuilder.append(sb).
                    append("(").append(pair).append(")\n");
        }

        return 1;
    }
}

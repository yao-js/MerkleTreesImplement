import java.util.ArrayList;
import java.util.List;

public class MerkleTest {
    public static void main(String[] args) {
        List<String> txList = new ArrayList<String>();
        txList.add("dssadas");
        txList.add("dsadsada");
        txList.add("ygbbbfdsad");
//        txList.add("ifdsbkfds");
        MerkleTrees merkleTrees = new MerkleTrees(txList);
        merkleTrees.Merkle_trees();
        String root = merkleTrees.getMerkleRoot();
        System.out.println(root);
    }
}

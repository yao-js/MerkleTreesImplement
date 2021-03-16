import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class MerkleTrees {

    private List<String> txList;
    private String MerkleRoot;

    //constructor of MerleTrees
    public MerkleTrees(List<String> txList){
        this.txList = txList;
    }

    public void Merkle_trees(){
        List<String> tempTxList = new ArrayList<String>();

        for (int i = 0; i < this.txList.size(); i++) {
            tempTxList.add(this.txList.get(i));
        }
        List<String> merkleTree = getMerkleRootFromTxList(txList);
        //循环，当merkle的长度只剩下一个的时候，就是所有transaction都集成了一个merkle root值
        while(merkleTree.size() != 1){
            merkleTree = getMerkleRootFromTxList(merkleTree);
        }
        this.MerkleRoot = merkleTree.get(0);
    }


    public String convertHashFromString(String transactionMess){
        byte[] cipher_byte;
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(transactionMess.getBytes());
            cipher_byte = md.digest();
            StringBuilder sb = new StringBuilder(2 * cipher_byte.length);
            for(byte b: cipher_byte) {
                sb.append(String.format("%02x", b&0xff) );
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    //从transaction list中循环提取两个相连的transaction，生成hash值，并等待下一次循环进行，直到遍历完整个list
    public List<String> getMerkleRootFromTxList(List<String> txList){
        List<String> newTxList = new ArrayList<String>();
        int index = 0;
        while(index < txList.size()){
            String left = txList.get(index);
            index++;
            String right = "";
            if(index != txList.size()){
                 right = txList.get(index);
                 index++;
            }
            String newHash = convertHashFromString(left + right);
            newTxList.add(newHash);
        }
        return newTxList;
    }

    public String getMerkleRoot() {
        return MerkleRoot;
    }
}

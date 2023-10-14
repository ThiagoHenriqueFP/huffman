package server.infrastructure.compressor;

import server.domain.Car;
import server.domain.CompressionResponse;
import server.domain.SymbolNode;
import server.infrastructure.priorityList.PriorityList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Compressor {
    private SymbolNode root;
    private Map<Character, String> charMap = new HashMap<>();
    private Map<String, Character> decompressMap = new HashMap<>();
    public CompressionResponse build(String s){
//        Set<Character> chars = new HashSet<>();
        PriorityList list = new PriorityList();

        for (int i = 0; i < s.length(); i++){
            try{
                Integer index = list.hasChar(s.charAt(i));
                if (index < 0){
                    SymbolNode sn = new SymbolNode(s.charAt(i));
                    list.add(sn);
                } else {
                    list.incrementFrequency(index);
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }

        root = null;

        while(list.size() > 1){
            SymbolNode x = list.remove();
            SymbolNode y = list.remove();

            SymbolNode z = new SymbolNode();
            z.setFreq(x.getFreq() + y.getFreq());
            z.setCharacter('-');
            z.setLeft(x);
            z.setRigth(y);

            root = z;

            list.add(z);
        }

        print(root, "-");
        String value = compactedResponse(charMap, s);
        System.out.println(value);

        CompressionResponse response = new CompressionResponse(root, value);

        charMap.clear();
        root = null;

        return response;
    }

    public void print(SymbolNode sn, String s) {
        if(sn.getLeft() == null && sn.getRigth() == null && root.getCharacter() != null){
            charMap.put(sn.getCharacter(), s);
//            System.out.println(sn.getCharacter() + " : " + s);
            return;
        }
        print(sn.getLeft(), s + "0");
        print(sn.getRigth(), s + "1");
    }

    public void buildTable(SymbolNode sn, String s) {
        if(sn.getLeft() == null && sn.getRigth() == null){
            decompressMap.put(s, sn.getCharacter());
//            System.out.println(sn.getCharacter() + " : " + s);
            return;
        }
        buildTable(sn.getLeft(), s + "0");
        buildTable(sn.getRigth(), s + "1");
    }

    public String compactedResponse (Map<Character, String> map, String msg) {
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < msg.length(); i++){
            String s = map.get(msg.charAt(i));
            response.append(s);
        }

        return response.toString();
    }

    public Car decompress(SymbolNode sn, String msg) {
        StringBuilder response = new StringBuilder(), temp = new StringBuilder();
        buildTable(sn, "-");
        for(int i = 0; i < msg.length(); i++){
            if (msg.charAt(i) == '-') {
                Character aux = decompressMap.get(temp.toString());

                if (aux != null)
                    response.append(aux);

                temp = new StringBuilder();
            }
            temp.append(msg.charAt(i));
        }

        System.out.println(response);

        ArrayList<String> fields = new ArrayList<>();
        StringBuilder aux = new StringBuilder();

        for (int i = 0; i  < response.length(); i++){
            if(response.charAt(i) == ','){
                fields.add(aux.toString());
                aux = new StringBuilder();
            } else {
                aux.append(response.charAt(i));
            }

            if (i == response.length() - 1)
                fields.add(aux.toString());
        }

        return new Car(
                fields.get(0),
                fields.get(1),
                fields.get(2),
                fields.get(3),
                Integer.parseInt(fields.get(4)),
                fields.get(5),
                fields.get(6)
        );
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Topic0 {

    public enum Syntax {
        Times, 
        Plus, Number
    }

    public interface Node
    {
        List<Node> GetChildren();
        String getValue();
    }

    public static class SyntaxNode implements Node {
        private Topic0.Syntax syntax;
        public SyntaxNode(Syntax syntax)
        {
            this.syntax = syntax;
        }
        public Syntax getSyntax(){
            return this.syntax;
        }
        @Override
        public String getValue(){
            return syntax.toString();
        }
        @Override
        public List<Topic0.Node> GetChildren() {
            List<Node> empty = new ArrayList<Node>();
            return empty;
        }
    }

    public static class BinaryExpression implements Node
    {
        
        private Topic0.Node leftNode;
        private Topic0.SyntaxNode operator;
        private Topic0.Node rightNode;
        public BinaryExpression(Node leftNode, SyntaxNode operator,Node right)
        {
            this.leftNode = leftNode;
            this.operator = operator;
            this.rightNode = right;

        }
        @Override
        public List<Topic0.Node> GetChildren() {
            List<Node> nodes = new ArrayList<Node>();
            nodes.add(leftNode);
            nodes.add(operator);
            nodes.add(rightNode);
            return nodes;
        }
        @Override
        public String getValue() {
            // TODO Auto-generated method stub
            return null;
        }
    }
    
    public static class NumberExpression implements Node{

        private String text;
        private Syntax value;
        public NumberExpression(String text, Syntax value) {
            this.text = text;
            this.value = value;
        }
        @Override
        public String getValue() {
            return this.text.toString();
        }

        @Override
        public List<Topic0.Node> GetChildren() {
            List<Node> empty = new ArrayList<Node>();
            return empty;
        }
    }

    public static class MakeTree
    {
        private List<Topic0.Node> nodes;

        public MakeTree(List<Node> Nodes)
        {
            nodes = Nodes;

        }

        public List<BinaryExpression> Sort(){
            
            List<Node> sorted = new ArrayList<Node>();
            List<BinaryExpression> BinaryTree = new ArrayList<BinaryExpression>();
            
            for(int i = nodes.size() - 1; i >= 0; i--){
                if(nodes.get(i) instanceof NumberExpression){
                    sorted.add((NumberExpression)nodes.get(i));

                }
                if(nodes.get(i) instanceof SyntaxNode){
                    sorted.add((SyntaxNode)nodes.get(i));
                }
            }
            for(int i = 1; i < sorted.size()-1; i+=2){
                BinaryExpression current = new BinaryExpression(sorted.get(i+1), (SyntaxNode)sorted.get(i), sorted.get(i-1));
                BinaryExpression previous = new BinaryExpression(sorted.get(i+1), (SyntaxNode)sorted.get(i), sorted.get(i-1));

                if(i != 1 && BinaryTree.size() > 0){
                    current = new BinaryExpression(sorted.get(i-1), (SyntaxNode)sorted.get(i), previous);
                    previous = new BinaryExpression(sorted.get(i-1), (SyntaxNode)sorted.get(i), previous);
                }
                BinaryTree.add(0, current);
            }
            return BinaryTree;
        }

    }

    public static class Split
    {
        private String input;
        private int _position = 0;

        public Split(String input){
            this.input = input;

        }
        public List<Node> sperate(){
            List<Node> inputs = new ArrayList<Node>();
            if(_position < input.length())
            {
                char temp;
                do 
                {
                    temp = getCurrent();
                    if(Character.isDigit(temp)){

                        int start = _position;
                        
                        while(Character.isDigit(temp))
                        {
                            _position++;
                            temp = getCurrent();
                        }
                        String text = input.substring(start, _position);
                        inputs.add(new NumberExpression(text, Syntax.Number));
                    }
                    else if(Character.isWhitespace(temp)){
                        _position++;
                    }
                    else if(temp == '+'){
                        _position++;
                        inputs.add(new SyntaxNode(Syntax.Plus));
                    }
                    else if(temp == '*'){
                        _position++;
                        inputs.add(new SyntaxNode(Syntax.Times));
                    }
                } while (temp != '\0');
                System.out.println("");
            }
            return inputs;
        }
        public char getCurrent(){
            if(_position >= input.length()){
                return '\0';
            }
            return input.charAt(_position);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        Split split = new Split(line);
        MakeTree make = new MakeTree(split.sperate());
        List<BinaryExpression> BinaryTree = make.Sort();
        for (BinaryExpression binaryExpression : BinaryTree) {
            if(binaryExpression.leftNode.getClass() != BinaryExpression.class){
                System.out.println(binaryExpression.leftNode.getValue());

            }
            System.out.println(binaryExpression.operator.getValue());
            if(binaryExpression.rightNode.getClass() != BinaryExpression.class){

                System.out.println(binaryExpression.rightNode.getValue());
            }
        }
    }

}

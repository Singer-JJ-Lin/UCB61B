public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        char[] chars = word.toCharArray();
        Deque<Character> deque = new LinkedListDeque<>();
        for(int i = 0; i < chars.length; i++) {
            deque.addLast(chars[i]);
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        for(int i = 0, j = word.length() - 1; i < j; i++, j--) {
            if(word.charAt(i) != word.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}

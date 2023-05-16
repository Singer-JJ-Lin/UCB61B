public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        char[] chars = word.toCharArray();
        Deque<Character> deque = new LinkedListDeque<>();
        for (char aChar : chars) {
            deque.addLast(aChar);
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        for (int i = 0, j = word.length() - 1; i < j; i++, j--) {
            if (word.charAt(i) != word.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null || word.length() <= 1) {
            return true;
        }

        int length = word.length();
        for (int i = 0, j = length - 1; i < j; i++, j--) {
            if (!cc.equalChars(word.charAt(i), word.charAt(j))) {
                return false;
            }
        }
        return true;
    }
}

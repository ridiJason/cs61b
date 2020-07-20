public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> wordTDeque = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++){
            wordTDeque.addLast(word.charAt(i));
        }
        return wordTDeque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordTDeque = wordToDeque(word);
        boolean temp = true;
        if(wordTDeque.getSize() == 0 || wordTDeque.getSize() == 1){
            return true;
        }
        while(temp && wordTDeque.getSize() > 1){
            if(wordTDeque.removeLast() == wordTDeque.removeFirst()){
                continue;
            }else{
                temp = false;
            }
        }
        return temp;
    }

    public boolean isPalindrome(Deque DequeWord){
        while (DequeWord.getSize() > 1){
            return DequeWord.removeFirst() == DequeWord.removeLast() && isPalindrome(DequeWord);
        }
        return true;
    }

    public boolean isPalindrome(Deque<Character> DequeWord, CharacterComparator cc){
        while(DequeWord.getSize() > 1){
            return cc.equalChars(DequeWord.removeFirst(),DequeWord.removeLast()) && isPalindrome(DequeWord,cc);
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        return isPalindrome(wordToDeque(word),cc);
    }



}

class Solution {
    public boolean canBeEqual(String s1, String s2) {

        char[] even1 = new char[]{s1.charAt(0), s1.charAt(2)};
        char[] even2 = new char[]{s2.charAt(0), s2.charAt(2)};
        
        char[] odd1 = new char[]{s1.charAt(1), s1.charAt(3)};
        char[] odd2 = new char[]{s2.charAt(1), s2.charAt(3)};
        
        java.util.Arrays.sort(even1);
        java.util.Arrays.sort(even2);
        java.util.Arrays.sort(odd1);
        java.util.Arrays.sort(odd2);
        
        return java.util.Arrays.equals(even1, even2) &&
               java.util.Arrays.equals(odd1, odd2);
    }
}
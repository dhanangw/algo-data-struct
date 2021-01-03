/**
 * Divisor Game
 * 
 * Alice and Bob take turns playing a game, with Alice starting first.
 * 
 * Initially, there is a number N on the chalkboard. On each player's turn, that
 * player makes a move consisting of:
 * 
 * Choosing any x with 0 < x < N and N % x == 0. Replacing the number N on the
 * chalkboard with N - x. Also, if a player cannot make a move, they lose the
 * game.
 * 
 * Return True if and only if Alice wins the game, assuming both players play
 * optimally.
 * 
 * Example 1:
 * Input: 2
 * Output: true
 * Explanation: Alice chooses 1, and Bob has no more moves.
 * 
 * Example 2:
 * Input: 3
 * Output: false
 * Explanation: Alice chooses 1, Bob chooses 1, and Alice has no more moves.
 * 
 */

class Solution {
    public boolean divisorGame(int N) {
        int turns = 0;
        boolean[] prevResults = new boolean[N + 1];
        return doesAliceWin(N, turns, prevResults);
    }

    public boolean doesAliceWin(int N, int turns, boolean[] prevResults) {
        if (N <= 0) {
            return turns % 2 == 0;
        }

        int x = -1;

        for (int i = 1; i <= N; ++i) {
            if (prevResults[i] == true || N % i == 0) {
                x = i;
                turns += 1;
                prevResults[i] = true;
                break;
            }
        }

        return doesAliceWin(N - x, turns, prevResults);
    }
}
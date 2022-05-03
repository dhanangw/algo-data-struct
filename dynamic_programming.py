from typing import Dict


def fibonacci(n: int) -> int:
    """Returns n-th fibonacci number.

    Uses regular recursion.
    Time: O(2^n) for each recursive level, we call fibonacci() twice.
    space: O(n) maximum recursion depth is n.

    Example:
        fib(0) returns 0.
        fib(1) returns 1.
        fib(2) returns 1.
        fib(3) returns 2.
        fib(4) returns 3.
        fib(5) returns 5.
        fib(6) returns 8.
    """
    if n == 0:
        return 0
    elif n in {1, 2}:
        return 1

    return fibonacci(n - 1) + fibonacci(n - 2)

def fibonacci_memo(n: int, memo: Dict[int, int] = {}) -> int:
    """Returns n-th fibonacci number.

    Uses dynamic programming memoization.

    "Memo" here is a Python dictionary.
    All the recursive calls modify the same dictionary ('memo' object has the same ID in the whole call stack).
    That allows to not explicitly return memo in the function.
    More info: https://www.geeksforgeeks.org/is-python-call-by-reference-or-call-by-value/

    Time: O(n) we don't need to go further into recursive stack if n is already in memo.
    Space: O(n) maximum recursion depth is n.
    """
    if n == 0:
        return 0
    elif n in memo:
        return memo[n]
    elif n in {1,2}:
        return 1

    print(f"before recursion; n: {n}, memo: {memo}, memoID: {id(memo)}")
    memo[n] = fibonacci_memo(n - 1, memo) + fibonacci_memo(n - 2, memo)
    print(f"after recursion; n: {n}, memo: {memo}, memoID: {id(memo)}")

    return memo[n]

def grid_traveler(m: int, n: int) -> int:
    """Given m*n grid, return number of possible way to traverse the grid from top-left cell to bottom-right cell.
    You can only move downward or rightward.

    Uses normal recursion.

    Time: O(2^(m+n)) because we need to calculate all possible combination of row-column.
    Space: O(m+n) because maximum recursion depth is m+n

    Example:
        grid_traveler(2,3) returns 3.
        grid_traveler(1,1) returns 0.
        grid_traveler(3,3) returns 6.
    """
    if m <= 0 or n <= 0:
        return 0
    elif m == 1 and n == 1:
        return 1

    move_down_result = grid_traveler(m - 1, n)
    move_right_result = grid_traveler(m, n - 1)

    return move_down_result + move_right_result

def grid_traveler_memo(m: int, n: int, memo: Dict[str, int] = {}) -> int:
    """Given m*n grid, return number of possible way to traverse the grid from top-left cell to bottom-right cell.
    You can only move downward or rightward.

    Uses dynamic programming memoization.

    Time: O(m*n) still not sure why.
    Space: O(m+n) maximum recursion depth is is m+n
    """
    key = f"{m},{n}"

    if key in memo:
        return memo[key]
    elif m <= 0 or n <= 0:
        return 0
    elif m == 1 and n == 1:
        return 1

    move_down_result = grid_traveler_memo(m - 1, n, memo)
    move_right_result = grid_traveler_memo(m, n - 1, memo)
    memo[key] = move_down_result + move_right_result

    return memo[key]

if __name__ == '__main__':
    # print(fibonacci_memo(5))
    print(grid_traveler_memo(18,18))


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


if __name__ == '__main__':
    print(fibonacci_memo(5))


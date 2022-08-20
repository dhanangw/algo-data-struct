"""Trie (pronounced 'try'), aka prefix tree, is a type of tree that maps prefix of a word"""
from typing import Dict, List


class TrieNode:
    """TrieNode represents a node in Trie

    a TrieNode can have multiple child.

    Property:
        char: represents character the 'TrieNode' represents.
        children: list of 'TrieNode' that is the children of current 'TrieNode'.
        is_end_of_word: represents whether the 'char' belongs to the end of a word.
    """
    def __init__(self) -> None:
        self.children: Dict[str, TrieNode] = {}
        self.is_end_of_word: bool = False

class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word: str) -> None:
        """insert a new 'word' into Trie."""
        current_node = self.root

        for char in word:
            if char not in current_node.children:
                new_node = TrieNode()
                current_node.children[char] = new_node

            current_node = current_node.children[char]
        current_node.is_end_of_word = True

    def search(self, word: str) -> bool:
        """checks whether 'word' exists in Trie."""
        current_node = self.root

        for char in word:
            if char not in current_node.children:
                return

            current_node = current_node.children[char]

        return current_node.is_end_of_word

    def starts_with(self, prefix: str) -> bool:
        """checks whether there is a word that prefixed with 'prefix'."""
        current_node = self.root

        for char in prefix:
            if char not in current_node.children:
                return False

            current_node = current_node.children[char]

        return True

    def delete(self, word: str) -> None:
        """delete 'word' from Trie."""
        current_node = self.root

        for char in word:
            if char not in current_node.children:
                print("no such word in Trie.")
                return

            current_node = current_node.children[char]

        current_node.is_end_of_word = False

if __name__ == '__main__':
    trie = Trie()
    trie.insert("apple")
    assert trie.search("apple")    # should return True
    assert  not trie.search("app") # should return False
    assert trie.starts_with("app")  # should return True
    trie.insert("app")
    assert trie.search("app")      # should return True
    trie.delete('apple')
    assert not trie.search("apple")      # should return False


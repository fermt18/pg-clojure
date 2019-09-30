(ns tictactoe.core-test
  (:require [clojure.test :refer :all]
            [tictactoe.core :refer :all]))

(deftest test-initialise-board
  (testing "Initialising board"
           (is (= (init-board) ["-" "-" "-" "-" "-" "-" "-" "-" "-"]))))

(deftest test-modify-cell-with-cross
  (testing "Test modify cell with cross"
           (is (= (modify-cell (init-board) 0 "X") ["X" "-" "-" "-" "-" "-" "-" "-" "-"]))))

(deftest test-modify-cell-with-circle
  (testing "Test modify cell with circle"
           (is (= (modify-cell (init-board) 3 "O") ["-" "-" "-" "O" "-" "-" "-" "-" "-"]))))

(deftest test-if-position-valid
  (testing "Test postions"
           (is (= (is-position-valid? -1) false))
           (is (= (is-position-valid? 9) false))
           (is (= (is-position-valid? 7) true))))

(deftest test-if-value-is-valid
  (testing "Test values"
           (is (= (is-value-valid? "x") false))
           (is (= (is-value-valid? "o") false))
           (is (= (is-value-valid? "X") true))
           (is (= (is-value-valid? "O") true))))

(deftest test-if-cell-is-free
  (testing "Test played cell"
          (is (= (is-cell-free? ["X" "-" "-" "-" "-" "-" "-" "-" "-"] 0) false))
          (is (= (is-cell-free? ["X" "-" "-" "-" "-" "-" "-" "-" "-"] 1) true))))

(deftest test-if-any-cell-is-free
  (testing "Test any cell free"
          (is (= (is-any-cell-free? ["X" "-" "-" "-" "-" "-" "-" "-" "-"]) true))
          (is (= (is-any-cell-free? ["X" "X" "X" "X" "X" "X" "X" "X" "X"]) false))))

(deftest test-modify-cell-with-wrong-character
  (testing "Test modify cell with wrong character"
           (is (= (modify-cell (init-board) 0 "J") ["-" "-" "-" "-" "-" "-" "-" "-" "-"]))))

(deftest test-modify-cell-out-of-bounds
  (testing "Test modify cell out of bounds"
           (is (= (modify-cell (init-board) 9 "X") ["-" "-" "-" "-" "-" "-" "-" "-" "-"]))))

(deftest test-modify-cell-already-played
  (testing "Test already played cell"
           (is (= (modify-cell ["X" "-" "-" "-" "-" "-" "-" "-" "-"] 0 "O") ["X" "-" "-" "-" "-" "-" "-" "-" "-"]))))

(deftest test-switch-player
  (testing "Test switch player"
           (is (= (switch-player "X") "O"))
           (is (= (switch-player "O") "X"))))

(deftest test-is-there-a-winner-horizontal
  (testing "Test if there is a winner in an horizontal row"
           (is (= (is-there-a-winner ["X" "X" "X" "-" "-" "-" "-" "-" "-"]) true))
           (is (= (is-there-a-winner ["X" "O" "X" "-" "-" "-" "-" "-" "-"]) false))
           (is (= (is-there-a-winner ["X" "O" "X" "-" "-" "-" "X" "X" "X"]) true))
           (is (= (is-there-a-winner ["-" "-" "-" "X" "X" "X" "-" "-" "-"]) true))
           (is (= (is-there-a-winner ["-" "-" "-" "-" "-" "-" "O" "O" "O"]) true))
           (is (= (is-there-a-winner ["-" "-" "-" "-" "-" "-" "-" "O" "O"]) false))
           (is (= (is-there-a-winner ["-" "-" "-" "-" "-" "-" "O" "O" "-"]) false))
           ))

(deftest test-is-there-a-winner-vertical
  (testing "Test if there is a winner in a vertical row"
           (is (= (is-there-a-winner ["X" "-" "-" "X" "-" "-" "X" "-" "-"]) true))
           (is (= (is-there-a-winner ["-" "O" "-" "-" "O" "-" "-" "O" "-"]) true))
           (is (= (is-there-a-winner ["-" "-" "O" "-" "-" "O" "-" "-" "O"]) true))
           ))

(deftest test-is-there-a-winner-diagonal
  (testing "Test if there is a winner in a vertical row"
           (is (= (is-there-a-winner ["X" "-" "-" "-" "X" "-" "-" "-" "X"]) true))
           (is (= (is-there-a-winner ["-" "-" "O" "-" "O" "-" "O" "-" "-"]) true))
           ))
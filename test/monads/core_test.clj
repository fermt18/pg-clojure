(ns monads.core-test
  (:require [clojure.test :refer :all]
           [monads.core :refer :all]))
		   
(deftest test-identity_monad_with_let
  (testing "identity monad with let"
    (is (= (identity_monad_with_let 2) 6))))
	
(deftest test-identity_monad_with_functions
  (testing "identity monad with functions"
    (is (= (identity_monad_with_functions 2) 6))))
	
(deftest test-identity_monad_with_bind_function
  (testing "identity monad with a bind function"
    (is (= (identity_monad_with_bind_function 2) 6))))
	
(deftest test-identity_monad_with_bind_function_and_nil_input
  (testing "identity monad with a bind function when input is nil"
    (is (= (identity_monad_with_bind_function nil) nil))))

(deftest test-identity_monad_with_domonad
  (testing "identity monad with domonad macro"
    (is (= (identity_monad_with_domonad 2) 6))))

	
(deftest test-sequence_monad_with_for
  (testing "sequence monad with for"
    (is (= (sequence_monad_with_for 5) [0 0 2 0 3 6 0 4 8 12]))))
	
(deftest test-sequence_monad_with_bind_function
  (testing "sequence monad with a bind function"
    (is (= (sequence_monad_with_bind_function 5) [0 0 2 0 3 6 0 4 8 12]))))
	
	
(deftest test-sane-parameters
  (testing "pretty-msg with sane parameters"
    (is (= (pretty-msg "test" 3) "***test***"))))
	
(deftest test-sane-parameters-with-nil
  (testing "pretty-msg with sane parameters"
    (is (= (pretty-msg-nil nil nil) nil))))
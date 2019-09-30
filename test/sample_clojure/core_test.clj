(ns sample-clojure.core-test
  (:require [clojure.test :refer :all]
            [sample-clojure.core :refer :all]))

(deftest a-test
  (testing "Immutability"
    (def pi 3.141)
	(+ pi 1)
    (is (= pi 3.141))))
	
(deftest b-test
  (testing "def creates mutable references. Do not use it to emulate mutation!"
      (def weird-pi 3.141)
	  (def other-pi weird-pi)
	  (def weird-pi 3)
	  (is (= weird-pi 3))
	  (is (= other-pi 3.141))))

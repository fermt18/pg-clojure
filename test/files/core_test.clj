(ns files.core-test
  (:require [clojure.test :refer :all]
            [files.core :refer :all]))

(deftest test-create-read-file
  (testing "Test that a file has been created"
           (create-file "resources/Example.txt" "This is a string")
           (is (= (read-file "resources/Example.txt") "This is a string"))
           (update-file "resources/Example.txt" "This is another string")
           (is (= (read-file "resources/Example.txt") "This is a stringThis is another string"))
           (delete-file "resources/Example.txt")
           (is (thrown? java.io.FileNotFoundException (read-file "resources/Example.txt")))
  ))
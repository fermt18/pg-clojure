(ns utread.core-test
  (:require [clojure.test :refer :all]
            [utread.core :refer :all]))


(deftest test-get-file-extension
  (testing "get last element of a split string by ."
           (is (= (get-file-extension "this.is.a.file.txt") "txt"))))


(deftest test-get-execution-time-from-header
  (testing "get the execution time from a header line. Returns the number of seconds or 0"
           (is (=
                (get-execution-time-from-header "Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.547 sec - in com.mango.cms.configuration.entity.common.tools.SectionsOrchestratorEntryDataServiceTest")
                0.547))))


(deftest test-get-execution-time-from-test
  (testing "get the execution time from a test line. Returns the number of seconds or 0"
           (is (=
                (get-execution-time-from-test "test(com.ServiceTest)  Time elapsed: 33.697 sec  <<< ERROR!")
                33.697))))


(deftest test-get-all-reports-from-folder
  (testing "get an array of files within a folder"
           (is (= (count (get-all-reports-from-folder "./resources/test-files/reports")) 4))))


(deftest test-find-string-in-line
  (testing "Find a string within a text and return the line if found, nil otherwise"
           (is (= (find-string-in-line
                   "Test set: com.mango.cms.configuration.entity.common.tools.SectionsOrchestratorEntryDataServiceTest"
                   "cms")
                  "Test set: com.mango.cms.configuration.entity.common.tools.SectionsOrchestratorEntryDataServiceTest"))
           (is (= (find-string-in-line
                    "Test set: com.mango.cms.configuration.entity.common.tools.SectionsOrchestratorEntryDataServiceTest"
                   "whatever") nil))))
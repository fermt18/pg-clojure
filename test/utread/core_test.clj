(ns utread.core-test
  (:require [clojure.test :refer :all]
            [utread.core :refer :all]))


(deftest test-get-file-extension
  (testing "get last element of a split string by ."
           (is (= (get-file-extension "this.is.a.file.txt") "txt"))))


(deftest test-get-execution-time
  (testing "get the execution time from a line. Returns the number of seconds or 0"
           (is (=
                (get-execution-time "Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.547 sec - in com.mango.cms.configuration.entity.common.tools.SectionsOrchestratorEntryDataServiceTest")
                0.547))))


(deftest test-get-all-files-from-folder
  (testing "get an array of files within a folder"
           (is (= (count (get-all-files-from-folder "./resources/test-files/reports")) 5))))


(deftest test-get-all-txt-files-from-sequence
  (testing "filters the list of files getting only the ones with txt extension"
           (is (=
                (count (get-all-txt-files-from-sequence (get-all-files-from-folder "./resources/test-files/reports")))
                4))))


(deftest test-find-string-in-line
  (testing "Find a string within a text and return the line if found, nil otherwise"
           (is (= (find-string-in-line
                   "Test set: com.mango.cms.configuration.entity.common.tools.SectionsOrchestratorEntryDataServiceTest"
                   "cms")
                  "Test set: com.mango.cms.configuration.entity.common.tools.SectionsOrchestratorEntryDataServiceTest"))
           (is (= (find-string-in-line
                    "Test set: com.mango.cms.configuration.entity.common.tools.SectionsOrchestratorEntryDataServiceTest"
                   "whatever") nil))
           ))


(deftest test-loop-over-file
  (testing "testing the file loop function"
           (loop-over-file
            (clojure.java.io/file "./resources/test-files/reports/com.mango.cms.storage.api.S3ConfigurationTest.txt")
            "elapsed")))
(ns utread.core
  (:require [clojure.java.io :as io])
  (:require [clojure.string :as str]))


(defn get-file-extension [filename]
  (last (str/split filename #"\.")))


(defn get-execution-time-with-errors [line]
  (let [commaseparated (str/split line #" ")]
    (if (> (count commaseparated) 4)
      (Double. (nth commaseparated 4))
      0.0)
    ))

(defn get-execution-time [line]
  (let [commaseparated (str/split line #",")]
    (if (> (count commaseparated) 4)
      (Double. (nth (str/split (nth commaseparated 4) #" ") 3))
      (get-execution-time-with-errors line))
    ))


(defn get-all-files-from-folder [folder]
  ;; lazy sequence in the file-seq collection that return true for the predicate
  (filter #(.isFile %) (file-seq (io/file folder))))


(defn get-all-txt-files-from-sequence [file-sequence]
  (filter
   (fn [x]
     (= (get-file-extension (.getName x)) "txt")) ;; from java.io.File.getName()
   file-sequence)) ;; function input


(defn get-all-reports-from-folder [folder]
  (get-all-txt-files-from-sequence (get-all-files-from-folder folder)))


(defn find-string-in-line [line string]
  (if (str/includes? line string)
    line
    nil))


(def counter 0)
(defn process-line [line]

  (let [timer (get-execution-time line)]
    (println line)
    (def counter (+ counter timer)))
  )


(defn loop-over-file [file string]
  (with-open [rdr (io/reader file)]
    (doseq [line (line-seq rdr)]
      (let [finding (find-string-in-line line string)]
        (if (not (= finding nil))
          (process-line finding)))
      )))


(defn -main
  "Main function"
  [& args]
  (def reports (get-all-reports-from-folder (first args)))
  (loop [num 0]
    (loop-over-file (nth reports num) "elapsed")
    (if (< num (- (count reports) 1))
      (recur (+ num 1))
      (println num "reports found")
    )
  )
  (println counter "seconds")
)

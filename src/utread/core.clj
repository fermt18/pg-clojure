(ns utread.core
  (:require [clojure.java.io :as io])
  (:require [clojure.string :as str]))


(defn get-file-extension [filename]
  ;(last (str/split filename #"\."))
  (-> filename
      (str/split #"\.")
      last))


(defn get-execution-time-from-test [line]
  (let [commaseparated (str/split line #" ")]
    (if (> (count commaseparated) 4)
      (Double. (nth commaseparated 4))
      0.0)))


(defn get-execution-time-from-header [line]
  (let [commaseparated (str/split line #",")]
    (if (> (count commaseparated) 4)
      (Double. (nth (str/split (nth commaseparated 4) #" ") 3))
      (get-execution-time-from-test line)))) ; not a header


(defn get-all-txt-files-from-sequence [file-sequence]
  (filter
   (fn [x]
     (= (get-file-extension (.getName x)) "txt")) ;; from java.io.File.getName()
   file-sequence)) ;; function input


(defn get-all-reports-from-folder [folder]
  (get-all-txt-files-from-sequence 
    (filter #(.isFile %) (file-seq (io/file folder)))))


(defn find-string-in-line [line string]
  (if (str/includes? line string)
    line
    nil))


(defn -main
  "Main function"
  [& args]
  (def counter 0)
  (def reports (get-all-reports-from-folder (first args)))
  (loop [num 0]
    (with-open [rdr (io/reader (nth reports num))]
      (doseq [line (line-seq rdr)]
        (let [finding (find-string-in-line line "elapsed")]
          (if (not (nil? finding))
            (let [timer (get-execution-time-from-header finding)]
              (println finding)
              (def counter (+ counter timer))
            )
          )
        )
      )
    )
    (if (< num (- (count reports) 1))
      (recur (+ num 1))
      (println num "reports found")
    )
  )
  (println counter "seconds")
)

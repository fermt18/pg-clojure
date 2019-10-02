(ns files.core
  (:require [clojure.java.io :as io]))

(defn create-file [path msg]
  (spit path msg))

(defn read-file [path]
  (slurp path))

(defn update-file [path msg]
  (with-open [w (io/writer path :append true)]
    (.write w (str msg))))

(defn delete-file [path]
  (io/delete-file path))
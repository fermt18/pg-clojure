(ns tictactoe.core)

(defn init-board []
  ["-" "-" "-" "-" "-" "-" "-" "-" "-"])

(defn is-any-cell-free? [board]
  (if (some #(= "-" %) board) ;return nil if predicate is never true
    true
    false))


(defn is-cell-free? [board position]
  (= (get board position) "-"))


(defn is-position-valid? [position]
  (and (< -1 position) (< position 9)))


(defn is-value-valid? [value]
  (or (= value "X") (= value "O")))


(defn modify-cell [board position value]
  (if (and
       (and (is-position-valid? position) (is-value-valid? value))
       (is-cell-free? board position))
    (assoc board position value)
    board))


(defn switch-player [player]
  (if (= player "O")
    "X"
    "O"))


(defn is-there-a-winner [board]
  (cond
    (and (not (= "-" (get board 0))) (apply = [(get board 0) (get board 1) (get board 2)]))
      true
    (and (not (= "-" (get board 3))) (apply = [(get board 3) (get board 4) (get board 5)]))
      true
    (and (not (= "-" (get board 6))) (apply = [(get board 6) (get board 7) (get board 8)]))
      true
    (and (not (= "-" (get board 0))) (apply = [(get board 0) (get board 3) (get board 6)]))
      true
    (and (not (= "-" (get board 1))) (apply = [(get board 1) (get board 4) (get board 7)]))
      true
    (and (not (= "-" (get board 2))) (apply = [(get board 2) (get board 5) (get board 8)]))
      true
    (and (not (= "-" (get board 0))) (apply = [(get board 0) (get board 4) (get board 8)]))
      true
    (and (not (= "-" (get board 2))) (apply = [(get board 2) (get board 4) (get board 6)]))
      true
    :else false))


(defn print-board [board]
  (println  (subvec board 0 3))
  (println  (subvec board 3 6))
  (println  (subvec board 6 9))
  )


(defn -main
  "Main function"
  [& args]
  (loop [board (init-board)
         player "X"]
    (print-board board)
    (if (and (is-any-cell-free? board) (not (is-there-a-winner board)))
      (recur
        (modify-cell board (Integer/parseInt (read-line)) player) (switch-player player))
      board
    )
  )
)
(ns monads.core)

(use 'clojure.algo.monads)


;; identity monad using let
(defn identity_monad_with_let [x]
  (let [a x
      b (inc a)]
    (* a b)))
  
;; identity monad using functions -> unreadable
;; fn[a] is called with argument x
;; fn[b] is called with argument (inc a)
(defn identity_monad_with_functions [x]
  ((fn [a] 
    ((fn [b] 
	  (* a b)) (inc a))) x) )
	  
;; identity monad using a bind function
;; allows to write a value before the function applied to it
(defn m-bind-value [value function]
  ;; Nil validation. (function value) represents the rest of following steps
  ;; this validation will apply to any further step without having to repeat it
  (if (nil? value) 
    nil
    (function value)))
(defn identity_monad_with_bind_function [x]
  (m-bind-value x (fn [a]
  (m-bind-value (inc a) (fn [b]
    (* a b))))) )
	
;; the same but using domonad macro from clojure.algo.monads
;; internally creates code as monad using functions example
(defn identity_monad_with_domonad [x]
  (domonad identity-m
    [a x
     b (inc a)]
     (* a b)))


;; sequence monad using for
(defn sequence_monad_with_for [x]
  (for [a (range x)
       b (range a)]
	 (* a b)))

;; sequence monad using a bind function
(defn m-bind-sequence [sequence function]
  (apply concat (map function sequence)))
(defn sequence_monad_with_bind_function [x]
  (m-bind-sequence (range x) (fn [a]
  (m-bind-sequence (range a) (fn [b]
  (list (* a b)))))))

  
(defn pretty-msg [msg asterisk-amount]
  (domonad identity-m
      [a asterisk-amount
	   b (clojure.string/join (repeat a "*"))
	   c (str b msg)]
     (str c b)))

;; now we send nil as parameter	 
(defn pretty-msg-nil [msg asterisk-amount]
  (domonad maybe-m
      [a asterisk-amount
	   b (clojure.string/join (repeat a "*"))
	   c (str b msg)
      :when (< (count msg) 0)]
      (str c b)))

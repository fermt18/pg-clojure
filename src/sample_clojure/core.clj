(ns sample-clojure.core
  (:gen-class))
  
;; Actions for Agents
(defn update-customer-name [state name] 
	(assoc state :name name))
(defn update-processed [state flag] 
	(assoc state :isProcessed flag))
	
(defn validator-invoice
  "Validator for Agent"
  [state]
  (if
    (and
	  (get state :isProcessed)
	  (clojure.string/blank? (get state :name)))
	false
	true))
	  
;; `def` to define names for truly global values.
;; `let` to bind local values
;; `defn` is just a wrapper over `def`, designed specifically to define functions.
;; Write pure functions as far as possible.

(defn -main
  "Main function"
  [& args]
  (println "Hello, World!")
  (def x (new java.util.ArrayList()))
  (.add x 10)
  (doto x (.add 20) (.add 30) (.add 40))
  (println x)

  ;; Agents are used for safely manage a mutable state in multi threading applications
  ;; Actions are sent to the agent but Send function returns before Action is processed
  ;; Not predictable when Action will be processed
  (def invoice-agent 
    (agent
  	  (hash-map :name nil, :isProcessed false)))
  (println (send invoice-agent update-customer-name "Fer"))
  (println invoice-agent)
  
  (set-validator! invoice-agent validator-invoice)
  (println (send invoice-agent update-processed true))
  
  (println (send invoice-agent update-customer-name nil))
  (println (agent-error invoice-agent))
  (shutdown-agents)
)

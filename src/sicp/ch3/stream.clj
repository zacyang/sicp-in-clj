(ns sicp.ch3.stream
  "since we are going impl our own version of force and delay"
  (:refer-clojure :exclude [force delay]))

(defn memo-proc 
  [proc]
  (let [*already-run*? (atom false)
        *result* (atom false)]
    (println "define=>" *already-run*?)
    (fn [] 

      (if-not @*already-run*?
               (do
                 (println "here")
                 (swap! *result* (fn [_] (proc)))
                 (swap! *already-run*? (fn [_] true))
                 *result*)
               *result*))))


(defn delay
  [proc]
  (memo-proc proc)
  )

(defn force 
  [delay]
  (delay)
)

;;; mem version of delay

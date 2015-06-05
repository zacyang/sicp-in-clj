(ns sicp.ch3.simulator)

(defn make-wire
  "make wire , a presentation of the logical wires"
  []
  (let [signal-value (atom 0)
        action-procedures '()]

    (defn- set-my-singnal! 
      [new-value]
      (if-not (= signal-value new-value)
        (swap! signal-value (fn [x] new-value))
        :done
        ))
    
    (defn- accept-action-procedure!
      [proc]
      (swap! action-procedures (fn [x] (cons proc action-procedures)))
      (proc)
      )

    (defn- get-signal-value
      []
      @signal-value)

      (fn dispatch [opt]
        (cond (= opt :get-signal) get-signal-value
              (= opt :set-signal!) set-my-singnal!
              (= opt :add-action!) accept-action-procedure!
              :default #(println "Unknow operation -- WIRE" opt)
              ))))

(defn get-signal
  [wire]
  (wire :get-signal))

(defn set-signal!
  [wire]
  (wire :set-signal!))

(defn add-action!
  [wire]
  (wire :add-action!))

(defn logical-not 
  [x]
  (cond (= x 0) 1
        (= x 1) 0
        :else (println "Invalid signal" x)))

(defn call-each
  [procs]
  (if (or (empty? procs) (nil? procs))
    :done
    (do
      ((first (doall procs)))
      (call-each (rest procs))
      )
    
))
(defn after-delay [delay proc]
)
;; (defn inverter 
;;   [input output]
;;   (defn- invert-input
;;     []
;;     (let [new-value (logical-not (get-signal input))]
;;       (after-delay inverter-delay
;;                    #(set-signal! output new-value))))

;;   (add-action! input invert-input)
;; )






;; (defn and-gate
;;   "logical or, for simulate or-gate behavious"
;;   [input-1 input-2 output]
  
;;   (defn and-action-procedure
;;     []
;;     (let [new-value (logical-and (get-signal input-1) (get-signal input-2))]
;;       (after-delay and-get-delay
;;                    (#(set-signal! out-put new-value)))

;; ))
;; )

;;; code on 3.3.4 just convert from scheme to clojure LOL

(def a (make-wire))
(def b (make-wire))
(def c (make-wire))

(def d (make-wire))
(def e (make-wire))
(def s (make-wire))




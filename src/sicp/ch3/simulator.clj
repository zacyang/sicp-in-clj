(ns sicp.ch3.simulator)

(def ^:private ^:dynamic
  *the-agenda* (atom '()))

(defn make-wire
  "make wire , a presentation of the logical wires"
  []
  (let [signal-value (atom 0)
        action-procedures (atom '())]

    (defn- set-my-singnal! 
      [new-value]
      (if-not (= signal-value new-value)
        (swap! signal-value (fn [x] new-value))
        :done
        ))
    
    (defn- accept-action-procedure!
      [proc]
      (swap! action-procedures (fn [x] (cons proc x)))
      (proc))

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
  [wire signal]
  (wire :set-signal!) signal)

(defn add-action!
  [wire action]
  ((wire :add-action!) action))

(defn logical-not 
  [x]
  (cond (= x 0) 1
        (= x 1) 0
        :else (println "Invalid signal" x)))

(defn call-each
  [procs]
  (if  (empty? procs) 
    :done
    (do
      ((first  procs))
      (call-each (rest procs))
      )))


(defn after-delay 
  [delay action]
  (add-to-agenda! (+ delay (current-time *the-agenda*)) 
                  action
                  *the-agenda*))

(def inverter-delay 1)

(defn inverter 
  [input output]
  (defn- invert-input
    []
    (let [new-value (logical-not (get-signal input))]
      (after-delay inverter-delay
                   (#(set-signal! output new-value)))))
  (add-action! input invert-input)
  :OK
)

(defn logical-and 
  [signal-1 signal-2]
  (if (and (= signal-1 1) (= signal-2 1)) 
    1
    0))

(def and-gate-delay 1)

(defn and-gate
  "logical or, for simulate or-gate behavious"
  [input-1 input-2 output]
  
  (defn and-action-procedure
    []
    (let [new-value (logical-and (get-signal input-1) (get-signal input-2))]
      (after-delay and-gate-delay
                   #(set-signal! output new-value)))))

;;; practise 3.28
(defn logical-or
  [signal-1 signal-2]
  (if (or (= signal-1 1) (= signal-2 1))
    1
    0))

(def or-gate-delay 1)

(defn or-gate
  [input-1 input-2 output]
  (defn or-gate-procedure
    []
    (let [new-value (logical-or (get-signal input-1) (get-signal input-2))]
      (after-delay and-gate-delay
                   #(set-signal! output new-value)))))

;;; 3.29
(defn or-gate-with-combination 
  [input-1 input-2 output]
  (let [w1 (make-wire)
        w2 (make-wire)
        w3 (make-wire)]
    (inverter input-1 w1)
    (inverter input-2 w2)
    (and-gate w1 w2 w3)
    (inverter w3 output)))



(defn make-time-segment 
  [time queue]
  (list time queue))

(defn segment-time 
  [segment]
  (first segment))

(defn segment-queue
  [segment]
  (last segment))

;;; define agenda
(defn make-agenda
  []
  (atom (list 0)))

(defn current-time
  [agenda]
  (first agenda))


;;; problem !!!!!!!!!!!!!
(defn set-current-time! 
  [agenda time]
  (swap! agenda (fn [x] (list (atom time)))))

(defn segments 
  [agenda]
  (last agenda))

(defn first-segment
  [agenda]
  (first (segments agenda)))

(defn rest-segment
  [agenda]
  (rest (segments agenda)))

(defn empty-agenda?
  [agenda]
  (empty? (segments agenda)))

;; (defn add-to-agenda! 
;;   [time action agenda]
;;   (defn- belongs-before?
;;     [segments]
;;     (or (empty? segments) (< time (segment-time (first segments)))))
;;   (defn- make-new-time-segment
;;     [time action]
;;     (let [q (make-queue)]
;;       (insert-queue! q action)
;;       (make-time-segment time q)))
;;   (defn add-to-segements! 
;;     [segments]
;;     ())


;; )


;;; code on 3.3.4 just convert from scheme to clojure LOL

;; (def a (make-wire))
;; (def b (make-wire))
;; (def c (make-wire))

;; (def d (make-wire))
;; (def e (make-wire))
;; (def s (make-wire))

(defn first-agenda-item
  [agenda])

(defn remove-first-agenda-item!
  [agenda])

;;; propagate
(defn propagate
  []
  (if (empty-agenda? *the-agenda*)
    :done
    (let [first-item (first-agenda-item *the-agenda*)]
      (first-item)
      (remove-first-agenda-item! *the-agenda*)
      (propagate))))

(defn probe
  [name wire]
  (add-action! wire
               #(
                 (println "----------")
                 (println name)
                 (println (current-time *the-agenda*))
                 (println "new-value = " (get-signal wire)))))


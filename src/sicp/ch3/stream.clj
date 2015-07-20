(ns sicp.ch3.stream
  "since we are going impl our own version of force and delay"
  (:refer-clojure :excludes [force delay])
)
;; reason for failure, this need to be a macro , parameter should not be eval
(defn memo-proc 
  [proc]
  (let [*already-run*? (atom false)
        *result* (atom false)]
    (fn [] 
      (if-not @*already-run*?
        (do
          (swap! *result* (fn [_] (proc)))
          (swap! *already-run*? (fn [_] true))
          @*result*)
        @*result*))))


(defn delay-eval
  [proc]
  (memo-proc proc))


(defn force-eval
  [delay]
  (delay))

;;; construct a stream

;;; defs 
;;; given any stream , matches (stream-car (cons-stream x y) ) => x
;;; and (stream-cdr (cons-stream x y)) => y

(defmacro cons-proc-macro 
  "sytacs macro makes user have no aware of the fn wrap"
  [ proc]
  `(fn [] (~@proc))
  )

(defn cons-stream 
  [x f]
  (list x (delay-eval f)))

(defn stream-car 
  [stream]
  (first stream))

;;(def ^:private the-empty-stream  (fn [] :the-empty-stream))
(def ^:private the-empty-stream  (cons-stream :the-empty-stream (fn [] :the-empty-stream)))

(defn stream-null?
  [stream]
  (= :the-empty-stream (stream-car stream)))

(defn stream-cdr
  [stream]
  (if (stream-null? stream)  stream
      (force-eval  (last stream))))

(defn stream-ref 
  [stream n]
  (if (zero? n)
    (stream-car stream)
    (stream-ref (stream-cdr stream) (dec n))))


(defn stream-for-each
  [proc stream]
  (if (stream-null? stream )
    stream
    (do (proc (stream-car stream))
        (stream-for-each (proc (stream-cdr stream))))))

(defn stream-map
  [proc stream]
  (if (stream-null? stream)
    the-empty-stream
    (cons-stream (proc (stream-car stream))
                 (fn []  (stream-map proc (stream-cdr stream))))))

(defn stream-filter
  [pred stream]
  (cond (stream-null? stream) the-empty-stream
        (pred (stream-car stream))
        (cons-stream (stream-car stream) (fn [] (stream-filter pred (stream-cdr stream))))
        :else (stream-filter pred (stream-cdr stream))))

;;; book example
(defn dived-by-3?
  [x]
  (zero? (mod x 3)))

(defn stream-enumerate-interval
  [low high]
  (if (> low high)
        the-empty-stream
        (cons-stream low (fn [] (stream-enumerate-interval (inc low) high)))))


(defn integers-starting-from 
  [n]
  (cons-stream n (fn [] (integers-starting-from (inc n)))))

(def integers (integers-starting-from 1))







(ns sicp.ch4.core)

(defn- self-evaluating?
  [exp]
  (cond (number? exp) true
        (string? exp) true
        :else false ))

(defn- variable?
  [exp]
  (symbol? exp))

(defn- lookup-variable-value 
  [exp env]
)

(defn- quoted?
  [exp]
  )

(defn- text-of-quotation
  [exp])

(defn- assignment? 
  [exp])

(defn- eval-assignment
  [exp env])

(defn- definition?
  [exp])

(defn- eval-definition
  [exp env])

(defn- if?
  [exp])

(defn- eval-if
  [exp env])

(defn- lambda?
  [exp])

(defn- lambda-parameters
  [exp])

(defn- lambda-body
  [exp])

(defn- make-procedure
  [parameters body env])

(defn- begin?
  [exp])

(defn- begin-actions
  [exp env])

(defn- eval-sequence
  [exp env])

(defn- cond?
  [exp])

(defn- cond->if
  [exp env])

(defn- application?
  [exp])

(defn- operator
  [exp])

(defn- operands
  [exp])

(defn- list-of-values
  [operands env])


(defn EVAL
  ^{:doc "my own version of eval, in convetion to avoid conflict and confusion, all MY version of the lisp will be capitalized."}
  [exp env]
  (cond (self-evaluating? exp) exp
        (variable? exp)   (lookup-variable-value exp env)
        (quoted? exp)     (text-of-quotation exp)
        (assignment? exp)  (eval-assignment exp env)
        (definition? exp) (eval-definition exp env)
        (if? exp) (eval-if exp env)
        (lambda? exp)     (make-procedure (lambda-parameters exp)
                                          (lambda-body exp)
                                          env)
        (begin? exp)      (eval-sequence (begin-actions exp) env)
        (cond? exp)       (EVAL (cond->if exp) env)
        (application? exp) (APPLY (EVAL (operator exp) env)
                                 (list-of-values (operands exp) env)
        )
        :else :ERROR
        ))

(defn APPLY
  [procedure arguments]
  )

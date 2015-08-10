(ns sicp.ch4.core)

(declare EVAL)
(declare APPLY)

(declare set-variable-value!)
(declare define-variable!)

(defn tagged-list?
  "check if the list is starts with some specific tag"
  [exp tag]
  (if (coll? exp) (= (first exp) tag)
      false))

(defn- self-evaluating?
  [exp]
  (cond (number? exp) true
        (string? exp) true
        :else false ))

(defn- variable?
  [exp]
  (symbol? exp))

(defn- quoted?
  [exp]
  (tagged-list? exp 'quoted))

(defn- text-of-quotation
  [exp]
  (second exp))

(defn- assignment? 
  [exp]
  (tagged-list? exp 'set!))

(defn- assignment-variable
  [exp]
  (second exp))

(defn- assignment-value 
  [exp]
  (nth exp 3))

(defn eval-assignment
  [exp env]
  (let [new-env   (set-variable-value! (assignment-variable exp)
                                       (EVAL (assignment-value exp) env)
                                       env)]
    (swap! env (fn [_] @new-env))
)

  :OK)


(defn definition?
  [exp]
  (tagged-list? exp 'define))

(defn make-lambda
  [parameters body]
  (cons 'lambda (cons parameters body)))

(defn definition-variable
  [exp]
  (if (symbol? (second exp))
    (second exp)
    (ffirst (rest exp))))

(defn definition-value 
  [exp]
  (if (symbol? (second exp)) (nth exp 2)
      (make-lambda (rest (first  (rest exp)))
                   (rest (rest exp)))))

(defn eval-definition
  [exp env]
  (let [new-env   (define-variable!
                    (definition-variable exp) 
                    (EVAL (definition-value exp) env)
                    env)]
    (swap! env (fn [_] @new-env))
    :OK
    ))

(defn if?
  [exp]
  (tagged-list? exp 'if))

(defn if-predicate 
  [exp]
  (second exp))

(defn if-consequent
  [exp]
  (nth exp 2))

(defn if-alternative
  [exp]
  (if (not (nil? (rest  (rest (rest exp)))))
    (nth exp 3)
    'false))

(defn eval-if
  [exp env]
  (if (true? (EVAL (if-predicate exp) env))
    (EVAL (if-consequent exp) env)
    (EVAL (if-alternative exp) env)))

(defn make-if 
  [pred consequent alternative]
  (list 'if pred consequent alternative))


(defn lambda?
  [exp]
  (tagged-list? exp 'lambda))

(defn lambda-parameters
  [exp]
  (second exp))

(defn lambda-body
  [exp]
  (rest (rest exp)))

(defn- make-procedure
  [parameters body env]
  (list 'procedure parameters body env))

(defn- begin?
  [exp]
  (tagged-list? exp 'begin))

(defn- begin-actions
  [exp env]
  (rest exp))

(defn- last-exp?
  [seq]
  (nil? (rest seq)))

(defn- first-exp
  [seq]
  (first seq))

(defn- rest-exps
  [seq]
  (rest seq))

(defn make-begin
  [seq]
  (cons 'begin seq))

(defn sequence->exp
  [seq]
  (cond (nil? seq) seq
        (last-exp? seq) (first-exp seq)
        :else (make-begin seq)))

(defn- eval-sequence
  [exps env]
  (cond (last-exp? exps) (EVAL (first-exp exps) env)
        :else (do  (EVAL (first-exp exps) env)
                   (eval-sequence (rest-exps exps) env))))

(defn cond?
  [exp]
  (tagged-list? exp 'cond))

(defn cond-clauses
  [clauses]
  (rest clauses))

(defn cond-predicate
  [clauses]
  (if  (list? clauses)
    (first clauses)
    :ERROR-NOT-VALID-PRED-OF-COND))

(defn cond-else-clause?
  [clauses]
  (= (cond-predicate clauses) 'else))

(defn cond-actions
  [exp]
  (rest exp))

(defn expand-clauses
  [exp]
  (if (or  (nil? exp) (empty? exp)) nil
      (let [first-clause (first exp)
            rest-clauses (rest exp)]
        (if (cond-else-clause? first-clause)
          (if (nil? rest-clauses)
            (sequence->exp (cond-actions first-clause))
            :ERROR-ELSE-NOT-LAST-OF-COND
            )
          (make-if (cond-predicate first-clause)
                   (sequence->exp (cond-actions first-clause))
                   (expand-clauses rest-clauses))))))

(defn cond->clauses
  [exp]
  (rest exp))

(defn- cond->if
  [exp env]
  (expand-clauses (cond->clauses exp)))

(defn- application?
  [exp]
  (list? exp))

(defn- operator
  [exp]
  (first exp))

(defn- operands
  [exp]
  (rest exp))

(defn- no-operands?
  [ops]
  (nil? ops))

(defn- first-operand
  [ops]
  (first ops))

(defn- rest-operands
  [ops]
  (rest ops))

;; APPLY related funtions

(defn primitive-procedure?
  [procedure])

(defn apply-primitive-procedure
  [procedure arguments])

(defn compound-procedure?
  [p]
  (tagged-list? p 'procedure))

(defn procedure-body
  [procedure]
  (nth procedure 3)
)

(defn procedure-parameters
  [procedure]
  (second procedure)
  )

(defn- procedure-enviroment
  [procedure]
  (nth procedure 4)
)

(defn enclosing-enviroment 
  [env]
  (atom (rest @env)))

(defn first-frame
  [env]
  (atom (first @env)))

(def the-empty-environment (atom '(())))

;;; env frame fns

(defn make-frame
  [variables values ]
  (atom (cons variables 
              values)))

(defn frame-variables
  [frame]
  (first @frame))

(defn frame-values
  [frame]
  (rest @frame))

(defn add-binding-to-frame!
  [var val frame]
  (swap! frame  (fn [f] (cons (cons var (first f))
                              (cons val (rest f))))))

(defn extend-enviroment
  ([vars vals base-env]
   (if (= (count vars) (count vars))
     (atom (cons @(make-frame vars vals) @base-env))
     (if (< (count vars) (count vals))
       :ERROR-ARGS-VALS-NOT-MATCH)))
  
  ([frame base-env]
   (atom (cons @frame @base-env))
   )
)

(defn- list-of-values
  [exps env]
  (if (no-operands? exps) '()
      (cons (EVAL (first-operand exps) env)
            (list-of-values (rest-operands exps) env))))

(defn lookup-variable-value 
  [var env]

  (defn env-loop 
    [env]
    (defn scan
      [vars vals]
      (cond (or  (nil? vars) (empty? vars))    (env-loop (enclosing-enviroment env))
            (= var (first vars))               (first vals)
            :else                              (scan (rest vars) (rest vals))))

    (if (= @env @the-empty-environment)
      :ERROR-NO-BOUND-VARIABLE
      (let [frame (first-frame env)]
        (scan (frame-variables frame)
              (frame-values frame)))))
  
  (env-loop env))


(defn set-variable-value!
  [var-looking-for val-to-be-set env]

  (defn find-and-change [vars vals]
    (cond
     (empty? vars)   '()
     (= (first vars) var-looking-for)  (conj (find-and-change (rest vars) (rest vals))  val-to-be-set) 
     :else (conj (find-and-change (rest vars) (rest vals)) (first vals))))

  (defn env-loop
    [e]
    
    (if (= @e @the-empty-environment) 
      :ERROR-TRY-SET-UNBOUND-VARIABLE
      (let  [frame        (first-frame e)
             frame-vars   (frame-variables frame)
             frame-vals   (frame-values frame)
             updated-vals (find-and-change frame-vars frame-vals)]
        
        (if (not= @(enclosing-enviroment e) @the-empty-environment)
          (extend-enviroment frame-vars updated-vals (env-loop (enclosing-enviroment e)))
          (extend-enviroment frame-vars updated-vals the-empty-environment)))))
  
  (env-loop env)
)

(defn define-variable! 
  [var val env]

  (let [frame       (first-frame env)
        frame-vars  (conj  (frame-variables frame) var)
        frame-vals  (conj (frame-values frame) val)]
    (extend-enviroment frame-vars frame-vals (enclosing-enviroment env))
    ))


(defn APPLY
  [procedure arguments]
  (cond (primitive-procedure? procedure)
        (apply-primitive-procedure procedure arguments)
        
        (compound-procedure? procedure)
        (eval-sequence (procedure-body procedure)
                       (extend-enviroment (procedure-parameters procedure)
                                          arguments
                                          (procedure-enviroment procedure)))

        :else :ERROR
        ))

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
        :else :ERROR))



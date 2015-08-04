(ns sicp.ch4.core-test
  (:use [midje.sweet]
        [sicp.ch4.core]))

(fact "EVAL should return exp itself when it's string or number"
      (let [env {}]
        (EVAL "STRING" env) => "STRING"
        (EVAL 1 env) => 1))

(fact "definition tests"
      (definition? 1) => false
      (definition? '(define something)) => true
      (definition? '(define something 1)) => true

      "definition-value should be able to get the def value as un-eval list"
      (definition-value '(define (some-fn arg1 arg2) (body))) => '(lambda (arg1 arg2) (body))
      (definition-value '(define some-fn (lambda (arg1 arg2) (body)))) => '(lambda (arg1 arg2) (body))
      
      (definition-value '(define some-value (+ 1 1))) => '(+ 1 1)

      "defnition-variable should be able to get the variable name"
      (definition-variable '(define (some-fn arg1 arg2) (body))) => some-fn
      (definition-variable '(define some-fn (lambda (arg1 arg2) (body)))) => some-fn
      (definition-variable '(define some-value 1)) => 'some-value)

(fact "lambda tests"
      (lambda? '(lambda (arg1 arg2) (body))) => true
      (lambda? '(hehe (arg1 arg2) (body))) => false
      
      (lambda-parameters '(lambda (arg1 arg2) body)) => '(arg1 arg2)

      (lambda-body '(lambda (arg1 arg2) body)) => '(body))

(fact "cond tests"
      (cond?  '(cond)) => true
      (cond->clauses '(cond test1 do1 test2 do2)) => '(test1 do1 test2 do2)

      (expand-clauses (cond->clauses '(cond (test1 do1) (test2 do2))))
      =>
      '(if test1 (begin do1) (if test2 (begin do2) nil)))

(fact "env tests"
      @(make-frame '(a b c) '(1 2 3)) => '((a b c) 1 2 3)
      @(make-frame '() '() ) => '(())

      (let [frame (make-frame '(a b c) '(1 2 3))]
        (frame-variables frame) => '(a b c)
        (frame-values frame) => '(1 2 3)
        )

      (add-binding-to-frame! 'c 1 (make-frame '() '())) => '((c) 1)
      (add-binding-to-frame! 'c 1 (make-frame '(a) '(1))) => '((c a) 1 1)
      (add-binding-to-frame! 'c 1 (make-frame '(a b) '(3 4))) => '((c a b) 1 3 4)

      "extend env"
      @(extend-enviroment '(e) '(42) (make-frame '() '())) => '(((e) 42) ())

      (let [extend-env (extend-enviroment '(e) '(42) (make-frame '() '()))]
        @(first-frame extend-env) => '((e) 42)
        (frame-variables (first-frame extend-env)) => '(e)
        (frame-values (first-frame extend-env)) => '(42)
        ))


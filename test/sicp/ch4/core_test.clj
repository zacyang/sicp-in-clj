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
      (definition-variable '(define some-value 1)) => some-value


)



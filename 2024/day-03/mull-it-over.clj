; (def input "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))")

; part one

(def mul #"mul\(([0-9]{1,3}),([0-9]{1,3})\)")

(def input (slurp "puzzle-input.txt"))

(def matches (re-seq mul input))

(def pairs (map rest matches))

(defn mult-pair [[x y]]
  (* (Integer/parseInt x) (Integer/parseInt y)))

(def products (map mult-pair pairs))

(def part-one-result (apply + products))

;(prn part-one-result)

; part two

(def input-2 "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))")

(def instructions #"mul\(([0-9]{1,3}),([0-9]{1,3})\)|do\(\)|don't\(\)")

; (def data (re-seq instructions input-2))
(def data (re-seq instructions input))
(prn data)

(def program
  (loop [items data
         result []
         skipping? false]
    (if (empty? items)
      result
      (let [item (first items)
            instr (first item)]
        (cond
          (= instr "don't()") (recur (rest items) result true)
          (= instr "do()") (recur (rest items) result false)
          skipping? (recur (rest items) result true)
          :else (recur (rest items) (conj result item) false))))))

(prn
     (->> program
         (map rest)
         (map mult-pair)
         (apply +)))

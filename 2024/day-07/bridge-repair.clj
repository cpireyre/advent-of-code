(require '[clojure.math.combinatorics :as combo])

; ["156" " 15 6"] => [156 [15 6]]
(defn parse-equation [[lhs rhs]]
  (let [operands (->> rhs
                      (re-seq #"\d+")
                      (map parse-long))]
    [(parse-long lhs) operands]))

(defn parse [file]
  (->> file
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #":"))
       (map parse-equation)))

(defn calculate [equation operators]
  (reduce (fn [acc [op n]]
            (op acc n))
          (first equation)
          (map vector operators (rest equation))))

(defn try-equations [equation fns]
  (let [ops (combo/selections fns (dec (count equation)))]
    (map #(calculate equation %) ops)))

; [83 [22 85]] => 0
; [190 [29 190]] => 190
(defn check [[result operands] fns]
    (if (some #{result} (try-equations operands fns))
      result
      0))

(defn part-1 [file]
  (let [eqs (parse file)
        res (map #(check % [+ *]) eqs)]
    (apply + res)))

; (println (part-1 "example.txt"))
; (println (part-1 "puzzle-input.txt"))

(defn || [a b]
  (parse-long (str a b)))

(defn part-2 [file]
  (let [eqs (parse file)
        res (map #(check % [+ * ||]) eqs)]
    (apply + res)))

; (println (part-2 "example.txt"))
(println (part-2 "puzzle-input.txt"))

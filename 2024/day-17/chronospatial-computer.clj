(require '[clojure.string :as s])
(require '[clojure.math :refer [pow round]])

(defn parse [input] 
  (->> input
       (#(s/split % #","))
       (map read-string)
       vec))

(def input "0,1,5,4,3,0")
(def machine {:program (parse input)
              :pc 0
              :A 729
              :B 0
              :C 0})

(defn cpu [{:keys [program pc A B C out] :as computer}]
  (let [[opcode operand] (take 2 (drop pc program))
        value (case operand
                0 0
                1 1
                2 2
                3 3
                4 A
                5 B
                6 C
                7 :crash)]
    (case opcode
      ; quot or / or floor-div or something else?
      0 (assoc computer
               :crash (= value :crash)
               :A (round (quot A (pow 2 value)))
               :pc (+ 2 pc))
      1 (assoc computer
               :pc (+ 2 pc)
               :B (bit-xor B operand))
      2 (assoc computer
               :crash (= value :crash)
               :pc (+ 2 pc)
               :B (mod value 8))
      3 (if (zero? A)
          (assoc computer :pc (+ 2 pc))
          (assoc computer :pc operand))
      4 (assoc computer
               :pc (+ 2 pc)
               :B (bit-xor B C))
      5 (assoc computer
               :crash (= value :crash)
               :out (conj out (mod value 8))
               :pc (+ 2 pc))
      6 (assoc computer
               :crash (= value :crash)
               :B (round (quot A (pow 2 value)))
               :pc (+ 2 pc))
      7 (assoc computer
               :crash (= value :crash)
               :C (round (quot A (pow 2 value)))
               :pc (+ 2 pc))
      )
    )
  )

(defn boot [program a]
  (loop [state 
         {:program program
          :pc 0 :A a :B 0 :C 0
          :initial-A a
          :out []}      
         ]
    (if (and (not (:crash state))
             (< (:pc state) (count (:program state))))
      (recur (cpu state))
      state)))

(println (time (boot (parse "0,1,5,4,3,0") 729)))
(println (time (boot (parse "2,4,1,7,7,5,1,7,0,3,4,1,5,5,3,0") 66752888)))
(println (time (boot (parse "0,3,5,4,3,0") 117440)))

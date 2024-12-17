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

(defn cpu [{:keys [program pc A B C] :as computer}]
  (let [[opcode operand] (take 2 (drop pc program))
        value (case operand
                0 0
                1 1
                2 2
                3 3
                4 A
                5 B
                6 C)]
    (case opcode
      ; quot or / or floor-div or something else?
      0 (assoc computer
               :A (round (quot A (pow 2 value)))
               :pc (+ 2 pc))
      5 (do
          (print (str (mod value 8) ","))
          (assoc computer :pc (+ 2 pc)))
      3 (if (zero? A)
          (assoc computer :pc (+ 2 pc))
          (assoc computer :pc operand))
      )
    )
  )

(defn run [computer]
  (loop [state computer]
    (when (< (:pc state) (count (:program state)))
      (recur (cpu state)))))

(run machine)

(def opcodes
  {
   0 :adv ; A divide
   1 :bxl ; B XOR load(?)
   2 :bst ; B store
   3 :jnz ; jump not zero
   4 :bxc ; B XOR C
   5 :out ; out
   6 :bdv ; B divide
   7 :cdv ; C divide
   }
  )


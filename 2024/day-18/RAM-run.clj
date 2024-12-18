(require '[clojure.string :as s])

(defn parse [file]
  (->> file
       slurp
       s/split-lines
       (map #(s/split % #","))
       (map #(map parse-long %))
       ))

(defn move [a]
  (fn [b]
    (mapv + a b)))

(def dirs [[0 1] [0 -1] [1 0] [-1 0]])

(defn in-bounds [[x y]]
  (and
    (<= 0 x 70)
    (<= 0 y 70)))


(loop [
       falling (parse "puzzle-input.txt")
       obstacles (into #{} (take 1023 falling))
       seen #{}
       queue [[[0 0] 0]]]  ; [position distance] pairs
  (let [[h dist] (first queue)
        t (rest queue)
        moves (->> (map (move h) dirs)
                   (filter in-bounds)
                   (remove seen)
                   (remove obstacles)
                   (remove (into #{} (first falling)))
                   (map #(vector % (inc dist))))]
    (cond
      (nil? h) (println "No path found!")
      (= h [70 70]) (println dist)
      :else (recur (rest falling)
                   (conj obstacles (first falling))
                   (conj seen h)
                   (into t moves)))))

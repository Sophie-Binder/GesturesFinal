from flask import Flask, request, jsonify
import pickle
import cv2
import numpy as np
import mediapipe as mp
import io
from PIL import Image

app = Flask(__name__)

with open('model1.p', 'rb') as f:
    model_data = pickle.load(f)
model = model_data['model']

mp_hands = mp.solutions.hands
hands = mp_hands.Hands(static_image_mode=True, min_detection_confidence=0.3)

def allowed_file(filename):
    return '.' in filename and filename.rsplit('.', 1)[1].lower() in {'png', 'jpg', 'jpeg'}

def extract_hand_landmarks(image):
    data_aux = []
    x_, y_ = [], []

    img_rgb = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)

    results = hands.process(img_rgb)
    if results.multi_hand_landmarks:
        for hand_landmarks in results.multi_hand_landmarks:
            for i in range(len(hand_landmarks.landmark)):
                x = hand_landmarks.landmark[i].x
                y = hand_landmarks.landmark[i].y

                x_.append(x)
                y_.append(y)

            for i in range(len(hand_landmarks.landmark)):
                x = hand_landmarks.landmark[i].x
                y = hand_landmarks.landmark[i].y
                data_aux.append(x - min(x_))
                data_aux.append(y - min(y_))
    return np.array(data_aux) if data_aux else None

@app.route('/predict', methods=['POST'])
def predict():
    if 'image' not in request.files:
        return jsonify({'error': 'No image provided'}), 400

    file = request.files['image']
    
    if file and allowed_file(file.filename):
        try:            
            image = Image.open(io.BytesIO(file.read()))
            image = np.array(image)

            landmarks = extract_hand_landmarks(image)
            if landmarks is None:
                return jsonify({'error': 'No hand landmarks detected'}), 400

            landmarks = landmarks.reshape(1, -1)

            prediction = model.predict(landmarks)[0]

            return jsonify({'prediction': prediction}), 200
        except Exception as e:
            return jsonify({'error': f'Error processing image: {str(e)}'}), 500
    else:
        return jsonify({'error': 'Invalid file format'}), 400

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)

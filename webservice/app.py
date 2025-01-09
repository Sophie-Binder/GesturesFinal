from flask import Flask, request, jsonify
from PIL import Image
import io
import numpy as np
import tensor  # Replace with your AI framework (e.g., TensorFlow)
from your_model import load_model, preprocess_image  # Replace with your model's specific methods

app = Flask(__name__)

# Load your AI model (adjust as per your framework)
model = load_model()  # Replace with code to load your trained model
model.eval()  # Set model to evaluation mode

def allowed_file(filename):
    """Check if the file has an allowed image extension."""
    return '.' in filename and filename.rsplit('.', 1)[1].lower() in {'png', 'jpg', 'jpeg'}

@app.route('/predict', methods=['POST'])
def predict():
    if 'image' not in request.files:
        return jsonify({'error': 'No image provided'}), 400
    
    file = request.files['image']
    
    if file and allowed_file(file.filename):
        try:
            # Read and preprocess the image
            image = Image.open(io.BytesIO(file.read())).convert('RGB')
            processed_image = preprocess_image(image)  # Replace with your preprocessing steps

            # Convert the image to a tensor (adjust as per your model's input format)
            input_tensor = torch.unsqueeze(torch.tensor(processed_image), 0)

            # Make prediction
            with torch.no_grad():
                output = model(input_tensor)
                prediction = torch.argmax(output, dim=1).item()  # Adjust to your model's output

            return jsonify({'prediction': prediction}), 200
        except Exception as e:
            return jsonify({'error': f'Error processing image: {str(e)}'}), 500
    else:
        return jsonify({'error': 'Invalid file format'}), 400

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)

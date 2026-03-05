from flask import Flask, request, jsonify
import os

app = Flask(__name__)

UPLOAD_FOLDER = 'GestureProjectPart1'
if not os.path.exists(UPLOAD_FOLDER):
    os.makedirs(UPLOAD_FOLDER)

@app.route('/upload', methods=['POST'])
def upload_video():
    if 'video' not in request.files:
        return jsonify({"error": "No video file provided"}), 400
    
    video = request.files['video']
    if video.filename == '':
        return jsonify({"error": "No filename provided"}), 400

    file_path = os.path.join(UPLOAD_FOLDER, video.filename)
    video.save(file_path)
    
    print(f"Successfully saved: {video.filename}")
    return jsonify({"message": "Upload successful", "filename": video.filename}), 200

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)
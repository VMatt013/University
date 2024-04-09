from Cryptodome.Cipher import PKCS1_OAEP
from Cryptodome.PublicKey import RSA
from Cryptodome.Hash import SHA256
from Cryptodome.PublicKey import ECC
from Cryptodome.Signature import DSS, __all__
from Cryptodome.PublicKey import DSA
from Cryptodome.Signature import pkcs1_15
import os


class User:
    def __init__(self, name, private_key, public_key):
        self.name = name
        self.private_key = private_key
        self.public_key = public_key

    def __str__(self):
        return f"User: {self.name}\nPrivate key: {self.private_key}\nPublic Key: {self.public_key}"


def create_rsa_key():
    key = RSA.generate(8192)
    print(str(key.e))
    print(str(key.d))
    return key


def create_text_file():
    file_name = input("Enter file name:")
    try:
        with open(file_name, 'w') as file:
            print(f"{file_name} file Created")
    except Exception as e:
        print(e)


def create_rsa_signature(private_key, input_file, output_file):
    if not os.path.exists(input_file) \
            or not isinstance(private_key, RSA.RsaKey) \
            or not private_key.has_private():
        print("Not valid key")
        return
    try:
        #signature=""
        with open(input_file, 'rb') as file_to_sign:
            data = file_to_sign.read()
            hash_obj = SHA256.new(data)
            signature = pkcs1_15.new(private_key).sign(hash_obj)

        with open(output_file, 'w') as signed_file:
            signed_file.write(str(signature))

    except Exception as e:
        print(e)


if __name__ == '__main__':
    create_rsa_key()

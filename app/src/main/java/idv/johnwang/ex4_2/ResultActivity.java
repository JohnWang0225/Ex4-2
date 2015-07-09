package idv.johnwang.ex4_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResultActivity extends ActionBarActivity{
    private EditText etHeight, etWeight;
    private Button btnBack, btnSubmit;
    Intent intent = new Intent();
    Bundle bundle = new Bundle();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        findViews();
    }
    
    private void findViews() {
        etHeight = (EditText) findViewById(R.id.etHeight);
        etWeight = (EditText) findViewById(R.id.etWeight);
        
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult();
            }
        });
        
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setResult(RESULT_CANCELED, intent);
            finish();
        }
    });
}

    private void setResult() {
        try{
            int Height = Integer.parseInt(etHeight.getText().toString());
            int Weight = Integer.parseInt(etWeight.getText().toString());

            if (String.valueOf(Height).isEmpty() || String.valueOf(Weight).isEmpty()){
                throw new Exception();
            }

            bundle.putInt("Height", Height);
            bundle.putInt("Weight", Weight);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), getString(R.string.text_inputError), Toast.LENGTH_SHORT).show();
            return;
        }
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
    public void btnClearOnClick(View view){
        etHeight.setText("");
        etWeight.setText("");
    }
}

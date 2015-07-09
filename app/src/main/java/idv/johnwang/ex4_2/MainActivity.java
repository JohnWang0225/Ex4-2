package idv.johnwang.ex4_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends ActionBarActivity {
    private Button btnClac, btnEnd;
        private TextView tvBMI, tvResult;
        private final int CALC_REQUEST = 0;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            findViews();
        }

    private void findViews() {
        tvBMI = (TextView) findViewById(R.id.tvBMI);
        tvResult = (TextView) findViewById(R.id.tvResult);
        
        btnClac = (Button) findViewById(R.id.btnCalc);
        btnClac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ResultActivity.class);
                startActivityForResult(intent, CALC_REQUEST);
            }
        });
        
        btnEnd = (Button) findViewById(R.id.btnEnd);
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != CALC_REQUEST){
            return;
        }
        
        switch (resultCode) {
            case RESULT_OK :
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                Bundle bundle = data.getExtras();
                int height = bundle.getInt("Height");
                int weight = bundle.getInt("Weight");
                double calc = weight/((height/100.0)*(height/100.0));

                String BMI = getString(R.string.text_bmi) + String.valueOf(decimalFormat.format(calc));
                tvBMI.setText(BMI);

                if (calc>=18.5 && calc<24) {
                    tvResult.setText(getString(R.string.text_normal));
                }else if(calc >= 24) {
                    tvResult.setText(getString(R.string.text_overweight));
                }else{
                    tvResult.setText(getText(R.string.text_tooThin));
                }
                break;
            
            case RESULT_CANCELED:
                tvBMI.setText(getString(R.string.text_bmi));
                tvResult.setText(getString(R.string.text_result));
                break;
        }
    }
}

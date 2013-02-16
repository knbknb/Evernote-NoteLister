package knbknb.helpformatter;
//not used at this time

import java.util.HashSet;
import java.util.Map;

import joptsimple.HelpFormatter;
import joptsimple.OptionDescriptor;

public class KeyValuePairFormatter implements HelpFormatter {
    public String format( Map<String, ? extends OptionDescriptor> options ) {
        StringBuilder buffer = new StringBuilder();
        for ( OptionDescriptor each : new HashSet<OptionDescriptor>( options.values() ) ) {
            buffer.append( lineFor( each ) );
        }
        return buffer.toString();
    }

    private String lineFor( OptionDescriptor descriptor ) {
        StringBuilder line = new StringBuilder( descriptor.options().toString() );
        line.append( System.getProperty( "line.separator" ) ).append( descriptor.description() );
        line.append( "#### These are the command line options this program expects:" ).append( descriptor.description() );
        line.append( "" ).append( descriptor.description() );
        line.append( ":  description = " ).append( descriptor.description() );
        line.append( ",  required = " ).append( descriptor.isRequired() );
        line.append( ",  accepts arguments = " ).append( descriptor.acceptsArguments() );
        line.append( ",  requires argument = " ).append( descriptor.requiresArgument() );
        line.append( ",  argument description = " ).append( descriptor.argumentDescription() );
        line.append( ",  argument type indicator = " ).append( descriptor.argumentTypeIndicator() );
        line.append( ",  default values = " ).append( descriptor.defaultValues() );
        line.append( System.getProperty( "line.separator" ) );
        return line.toString();
    }
}